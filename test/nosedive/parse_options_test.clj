(ns nosedive.parse-options-test
  (:require [clojure.test :refer :all]
            [clojure.tools.cli :refer [parse-opts]]
            [nosedive.parse-options :refer :all]))

(deftest check-valid-actions-test
  (testing "No actions is correct"
   (let [opts (parse-opts [] cli-options)
         cva (check-valid-actions opts)
         {:keys [status result]} cva]
    (is (= {:status :right :result result} cva)))))
