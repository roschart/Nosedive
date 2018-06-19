(ns nosedive.parse-options
  (:require [clojure.tools.cli :refer [parse-opts]]
            [tri-either.core :as e]
            [nosedive.migrations :refer [migrate]]))

(def required-opts #{:creator :description :person :vote})

(defn missing-required?
  "Returns true if opts is missing any of the required-opts"
  [opts]
  (not-every? opts required-opts))


(def cli-options
  ;; An option with a required argument
  [["-d" "--date DATE" "Date ddMMyyyy"
    :default (java.util.Date.)
    :parse-fn #(.parse  (java.text.SimpleDateFormat. "ddMMyyyy") %)]
   ;; A non-idempotent option
   ["-c" "--creator  WHO" "Who vote"]
   ["-D" "--description  DESCRIPTION" "The descirption"]
   ["-p" "--person PERSON" "The person that is voted"]
   ["-v" "--vote vote" "The vote"
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 6) "Must be a number between 1 and 5"]]
   ["-h" "--help"]])

(defn check-valid-actions [options]
  (let [args (:arguments options)
        n (count args)]
    (if (> n 0)
      (case (first (:arguments options))
          "migrate" {:status :left :result {:type :action :action migrate}}
          {:status :error :result (str "Argument not valid")})
      {:status :right :result options})))

(defn check-errors [options]
  (if (:errors options)
    {:status :error :result (:errors options)}
    {:status :right :result options}))

(defn check-missing [options]
  (if (missing-required? (:options options))
    {:status :error :result (:summary options)}
    {:status :right :result options}))

(defn check-help [options]
  (if (get-in options [:options :help])
    {:status :error :result (:summary options)}
    {:status :right :result options}))


(defn check-args [args]
  (-> (parse-opts args cli-options)
      check-valid-actions
      (e/chain check-errors)
      (e/chain check-missing)
      (e/chain check-help)
      (e/map :options)))
  
