(ns tri-either.core
    (:refer-clojure :exclude [map]))

(defn chain [data next]
  (case (:status data)
    :left     data
    :right   (next (:result data))
    {:status :left :result (str "data not have :status in chain" next)}))

(defn debug 
  ([data] (debug data ""))
  ([data msg]
   (println (str "DEBUGING:" msg)) 
   (println data)
   data))

  
(defn map [data fun]
  (case (:status data)
      :right     (update data :result fun)
      data))
