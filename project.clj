(defproject nosedive "0.1.0-SNAPSHOT"
  :description "Rate other persons like in the nosedive (blak mirror serie)"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/java.jdbc "0.7.6"]
                 [org.clojure/tools.cli "0.3.7"]
                 [org.xerial/sqlite-jdbc "3.23.1"]
                 [migratus "1.0.6"]
                 [mount "0.1.12"]]
  :main ^:skip-aot nosedive.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]]
                   :source-paths ["env/dev"]
                   :resource-paths  ["resources/dev"]
                   :repl-options {:init-ns user}}
             :prod {:source-paths ["env/prod"]
                    :resource-paths ["resources/prod"]}}
  :plugins [[lein-ancient "0.6.15"]
            [lein-pprint "1.1.2"]])
