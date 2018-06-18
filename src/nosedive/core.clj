(ns nosedive.core
  (:require [clojure.java.jdbc :refer :all]
            [nosedive.parse-options :as p]
            [tri-either.core :as e]
            [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:gen-class))

(defn save! [data]
  (let [db (:db (edn/read-string  (slurp (io/resource "config.edn"))))]
    (insert! db :votes data)
    (let [output (query db "select * from votes")]
      (println (last output)))))

(defn process! [data]
  (case (:status data)
      :error   (println  (:result data))
      :success (save!    (:result data))
      :execute ((:result data))))

(defn -main  [& args]
  (-> args
      p/check-args
      process!))
    
