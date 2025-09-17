(ns helpers.api-version)

(defn api-version
  [request]
  {:status 200 :body {:version "1.0.0"}})