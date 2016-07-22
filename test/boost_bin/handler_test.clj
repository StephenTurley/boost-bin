(ns boost-bin.handler-test
	(:require [speclj.core :refer :all]
						[ring.mock.request :as mock]
						[boost-bin.handler :as underTest]
            [boost-bin.db :as db]
						[ring.adapter.jetty :refer [run-jetty]]
						[clojure.java.io :as io]))

(describe "The routes"
  (describe "main route"
    (let [response (underTest/app (mock/request :get "/"))]
      (it "should return a 200"
          (should= 200 (:status response)))
      (it "should return index.html"
          (should= (slurp (io/resource "public/index.html")) (slurp (:body response))))))
	(describe "csv upload"
		(with-redefs [db/save-data-log (fn [stub] stub)]
			(let [response (underTest/app (mock/request :post "/datalog" {"data" "someData"}))]
			 (it "should call saveDataLog with the csv and redirect"
				 (should= 302 (:status response))))))
  (describe "not-found route"
    (let [response (underTest/app (mock/request :get "/invalid"))]
      (it "should return a 400"
          (should= 404 (:status response))))))

(defn jetty-port
	[jetty-args]
	(:port (nth jetty-args 1)))

(defmacro mock-jetty
  [someTest]
  `(with-redefs [run-jetty (fn [handler# args#] [handler# args#])] ~someTest))

(describe "The app"
  (it "should default to port 8080"
      (mock-jetty
        (should= 8080 (jetty-port (underTest/-main)))))
	(it "should overide the port with first arg"
      (mock-jetty
        (should= 8081 (jetty-port (underTest/-main "8081")))))
	(it "should set the app handler"
      (mock-jetty
        (should= underTest/app (first (underTest/-main))))))


