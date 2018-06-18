(defproject nosedive "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/java.jdbc "0.7.6"]
                 [org.clojure/tools.cli "0.3.7"]
                 [org.xerial/sqlite-jdbc "3.23.1"]
                 [migratus "1.0.6"]]
  :main ^:skip-aot nosedive.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:resource-paths  ["resources/dev"]}
             :prod {:resource-paths ["resources/prod"]}}
  :plugins [[lein-ancient "0.6.15"]
            [migratus-lein "0.5.7"]
            [lein-pprint "1.1.2"]]
  :migratus {:store             :database
             :migration-dir     "migrations"
             :db  {:classname   "org.sqlite.JDBC"
                   :subprotocol "sqlite"
                   :subname     "db/database.db"}})
