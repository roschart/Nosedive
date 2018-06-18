(ns nosedive.migrations
    (:require [migratus.core :as migratus]
              [clojure.edn :as edn]
              [clojure.java.io :as io]))

(defn migrate []
    (let [config (edn/read-string  (slurp (io/resource "config.edn")))]
        (migratus/migrate config)))
