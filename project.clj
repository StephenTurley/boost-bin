(defproject boost-bin "0.1.0-SNAPSHOT"
  :description "An app for sharing Cobb Accessport logs"
  :url "http://www.boostbin.com"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [org.clojure/data.csv "0.1.3"]
                 [com.novemberain/monger "3.0.2"]
                 [ring/ring-json "0.4.0"]]
  :plugins [
            [lein-ring "0.9.7"]
            [speclj "3.3.0"]
            [lein-npm "0.6.2"]]
  :ring {:handler boost-bin.handler/app}
  :main boost-bin.handler
  :profiles {
              :uberjar {:aot :all}
              :dev {:dependencies [
                        [ring/ring-mock "0.3.0"]
                        [speclj "3.3.2"]
                        [clj-fakes "0.5.0"]]}}
  :npm {:dependencies [[jquery "3.1.0"]
                      [jquery-flot "0.8.3"]
                      [underscore "1.8.3"]]
        :root "resources/public/js/"})
