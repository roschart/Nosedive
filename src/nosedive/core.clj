(ns nosedive.core
  (:require [clojure.java.jdbc :refer :all]
            [nosedive.parse-options :as p]
            [either.core :as e]
            [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:gen-class))

(defn save! [data]
  (let [db (:db (edn/read-string  (slurp (io/resource "config.edn"))))]
    (insert! db :votes data)
    (let [output (query db "select * from votes")]
      (println (last output)))))

(defn process! [data]
  (let [{status :status result :result} data]
    (case status
        :left   (cond (= :action (-> result :type)) ((-> result :action))
                      :else (println result))
        :right (save!  result))))

(defn -main  [& args]
  (-> args
      p/check-args
      process!))
    
