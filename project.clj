(defproject boost-bin "0.1.0-SNAPSHOT"
  :description "An app for sharing Cobb Accessport logs"
  :url "http://www.boostbin.com"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]]
  :plugins [
            [lein-ring "0.9.7"]
            [speclj "3.3.0"]]
  :ring {:handler boost-bin.handler/app}
	:main boost-bin.handler
  :profiles {
              :uberjar {:aot :all}
              :dev {:dependencies [
                        [ring/ring-mock "0.3.0"]
                        [speclj "3.3.2"]]}})
