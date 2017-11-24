(defproject situated-program-challenge "0.1.0-SNAPSHOT"
  :description "Spec for Situated Program Challenge at clj-nakano #2
               https://clj-nakano.connpass.com/event/71884/"
  :url "https://github.com/clj-nakano/situated-program-challenge"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 [migratus                  "1.0.1"]
                 [com.fzakaria/slf4j-timbre "0.3.2"]
                 [com.taoensso/timbre       "4.10.0"]
                 [org.postgresql/postgresql "9.4-1206-jdbc42"]]
  :migratus {:store :database
             :migration-dir "migrations"
             :init-script   "init.sql"
             :db "postgres://meetup:password123@localhost:5432/meetup"}
  :profiles {:dev {:source-paths ["dev"]
                   :plugins [[migratus-lein "0.5.3"]]}})
