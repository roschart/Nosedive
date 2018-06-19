(ns user 
    (:require [mount.core :as mount]
              [clojure.tools.namespace.repl :refer [refresh]]))

(defn restart []
    (mount/stop)
    (mount/start))
