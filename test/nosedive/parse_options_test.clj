(ns nosedive.parse-options-test
  (:require [clojure.test :refer :all]
            [clojure.tools.cli :refer [parse-opts]]
            [nosedive.parse-options :refer :all]
            [nosedive.migrations :refer [migrate]]))

(deftest check-valid-actions-test
  (testing "No actions is correct"
   (let [opts (parse-opts [] cli-options)
         cva (check-valid-actions opts)
         {:keys [status result]} cva]
    (is (= {:status :right :result result} cva))))
  
  (testing "action migrate"
   (let [opts (parse-opts ["migrate"] cli-options)
         cva (check-valid-actions opts)
         {:keys [status result]} cva]
    (is (= {:status :left :result {:type :action :action migrate}} cva)))))

(deftest check-errors-test
  (testing "No must be errors in the parser"
   (let [opts (parse-opts ["-v 6"] cli-options)
         ce (check-errors opts)
         {:keys [status result]} ce]
    (is (= {:status :right :result result} ce)))))
