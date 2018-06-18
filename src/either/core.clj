(ns either.core
    (:refer-clojure :exclude [map]))

(defn chain [data next]
  (case (:status data)
    :error
    data
    :success
    (next (:result data))
    {:status :error :result (str "data not have :status in chain" next)}))

(defn debug 
  ([data] (debug data ""))
  ([data msg]
   (println (str "DEBUGING:" msg)) 
   (println data)
   data))

(defn either [data fail success]
  (case (:status data)
    :error
    (fail (:result data))
    :success
    (success (:result data))))
    
(defn map [data fun]
  (case (:status data)
      :error
      data
      :success
      (update data :result fun)))
