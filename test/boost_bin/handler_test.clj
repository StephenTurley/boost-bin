(ns boost-bin.handler-test
  (:require [speclj.core :refer :all]
            [ring.mock.request :as mock]
            [boost-bin.handler :as underTest]
            [ring.adapter.jetty :refer [run-jetty]]
						[clojure.java.io :as io]))

(describe "The routes"
  (describe "main route"
    (let [response (underTest/app (mock/request :get "/"))]
      (it "should return a 200"
          (should= 200 (:status response)))
      (it "should return Hello World"
          (should= (io/file "public/index.html") (:body response)))))

  (describe "not-found route"
    (let [response (underTest/app (mock/request :get "/invalid"))]
      (it "should return a 400"
          (should= 404 (:status response))))))

(defn jetty-port
	[jetty-args]
	(:port (nth jetty-args 1)))

(describe "The app"
  (it "should default to port 8080"
    (with-redefs [run-jetty (fn [handler opts] [handler opts])]
      (should= 8080 (jetty-port (underTest/-main)))))
	(it "should overide the port with first arg"
		(with-redefs [run-jetty (fn [handler opts] [handler opts])]
			(should= 8081 (jetty-port (underTest/-main "8081")))))
	(it "should set the app handler"
		(with-redefs [run-jetty (fn [handler opts] [handler opts])]
			(should= underTest/app (first (underTest/-main))))))
