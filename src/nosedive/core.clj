(ns nosedive.core
  (:require [clojure.java.jdbc :refer :all]
            [clojure.tools.cli :refer [parse-opts]])
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

(def cli-options
  ;; An option with a required argument
  [["-d" "--date DATE" "Date ddMMyyyy"
    :default (java.util.Date.)
    :parse-fn #(.parse  (java.text.SimpleDateFormat. "ddMMyyyy") %)]
   ;; A non-idempotent option
   ["-c" "--creator  WHO" "Who vote"]
   ["-D" "--descrition  DESCRIPTION" "The descirption"]
   ["-p" "--person PERSON" "The person that is voted"]
   ["-v" "--vote vote" "The vote"
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 6) "Must be a number between 0 and 65536"]]])

(defn -main
  [& args]
  (let [options (parse-opts args cli-options)]
    (println options)))
    ; (insert! db :votes testdata)

    ; (def output
    ;   (query db "select * from votes"))

    ; (keys (first output))
    ; (:body (first output))
    ; (println output)))
