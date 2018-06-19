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
   (let [opts (parse-opts ["-v" "6"] cli-options)
         ce (check-errors opts)
         {:keys [status result]} ce]
    (is (= {:status :left :result result} ce))))
  
  (testing "No erros when argumentes are correct"
   (let [opts (parse-opts ["-v" "5"] cli-options)
         ce (check-errors opts)
         {:keys [status result]} ce]
    (is (= {:status :right :result result} ce))))
  
  (testing "No errors when empty arguments"
    (let [opts (parse-opts [] cli-options)
          ce (check-errors opts)
          {:keys [status result]} ce]
      (is (= {:status :right :result result} ce)))))

(deftest check-missing-test
  (testing "Error when miss some mandatori parameter"
   (let [opts (parse-opts [] cli-options)
         ce (check-missing opts)
         {:keys [status result]} ce]
    (is (= {:status :left :result result} ce))))
  (testing "No errors when all paremeters are sended"
   (let [opts (parse-opts ["-pJose" "-cAntonio" "-v3" "-D" "por que mola"] cli-options)
         ce (check-missing opts)
         {:keys [status result]} ce]
    (is (= {:status :right :result result} ce)))))
