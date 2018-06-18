(ns nosedive.core
  (:require [clojure.java.jdbc :refer :all]
            ; [clojure.tools.cli :refer [parse-opts]]
            [nosedive.parse-options :refer :all]
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


(defn process [data]
  (println (str "Los datos a procesar son:" data)))

(defn -main
  [& args]
  (let [{:keys [options arguments summary errors] :as po}  (parse-opts args)]
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
