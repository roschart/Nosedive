(ns tri-either.core
    (:refer-clojure :exclude [map]))

(defn chain [data next]
  (case (:status data)
    :error     data
    :execute   data 
    :success   (next (:result data))
    {:status :error :result (str "data not have :status in chain" next)}))

(defn debug 
  ([data] (debug data ""))
  ([data msg]
   (println (str "DEBUGING:" msg)) 
   (println data)
   data))

  
(defn map [data fun]
  (case (:status data)
      :success     (update data :result fun)
      data))
