(ns nosedive.core
  (:require [clojure.java.jdbc :refer :all])
  (:gen-class))

(def testdata
  {:date "2018-96-12",
   :creator "jl.balirac@payvision.com",
   :person "c.cobo@payvision.com",
   :vote 5,
   :description "Me invitó a un pincho de tortilla"})


(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"})


(defn -main
  [& args]
  (println "Hello, World!")
  (insert! db :votes testdata)

  (def output
    (query db "select * from votes"))

  (keys (first output))
  (:body (first output))
  (println output))
