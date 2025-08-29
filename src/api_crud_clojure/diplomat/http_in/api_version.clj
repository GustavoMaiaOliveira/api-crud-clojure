(ns api-crud-clojure.diplomat.http-in.api-version
  (:require [api-crud-clojure.controller.api-version :as controller.api-version]))

(defn api-version
  [request]
  (controller.api-version/api-version request))