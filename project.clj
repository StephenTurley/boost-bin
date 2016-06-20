(defproject boost-bin "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler boost-bin.handler/app}
	:main boost-bin.handler
  :profiles
  {:uberjar {:aot :all}
	 :dev {:dependencies [[ring/ring-mock "0.3.0"]]}})
