(ns nosedive.core
  (:require [clojure.java.jdbc :refer :all]
            [clojure.tools.cli :refer [parse-opts]]
            [either.core :as e])
  (:gen-class))

(def testdata
  {:date "2018-06-12",
   :creator "jl.balirac@payvision.com",
   :person "c.cobo@payvision.com",
   :vote 5,
   :description "Me invitó a un pincho de tortilla"})


(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"})

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

(defn check-errors [options]
  (if (:errors options)
    {:status :error :result (:errors options)}
    {:status :success :result options}))

(defn check-missing [options]
  (if (missing-required? (:options options))
    {:status :error :result (:summary options)}
    {:status :success :result options}))

(defn check-help [options]
  (if (get-in options [:options :help])
    {:status :error :result (:summary options)}
    {:status :success :result options}))

(defn process [data]
  (println (str "Los datos a procesar son:" data)))

(defn -main
  [& args]
  (let [{:keys [options arguments summary errors] :as po}  (parse-opts args cli-options)]
    (-> po
        check-errors
        (e/chain check-missing)
        (e/chain check-help)
        (e/map :options)
        (e/either println process))))
    ; (insert! db :votes testdata)

    ; (def output
    ;   (query db "select * from votes"))

    ; (keys (first output))
    ; (:body (first output))
    ; (println output)))
