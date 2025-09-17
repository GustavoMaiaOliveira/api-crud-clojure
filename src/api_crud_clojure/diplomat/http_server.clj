(ns api-crud-clojure.diplomat.http-server
  (:require [io.pedestal.http.route :as route]
            [users.create :as users.create]
            [users.delete :as users.delete]
            [users.read :as users.read]
            [users.update :as users.update]
            [api-crud-clojure.helpers.interceptors :as interceptors]
            [api-crud-clojure.helpers.api-version :as api-version]))

(def routes
  (route/expand-routes
    #{["/api-version"
       :get api-version/api-version
       :route-name :api-version]
      ["/users"
       :get users.read/read-users
       :route-name :get-all-users]
      ["/users"
       :post (conj interceptors/common-interceptors users.create/create-new-user)
       :route-name :create-users]
      ["/users/:id"
       :delete users.delete/delete-users-by-id
       :route-name :delete-users]
      ["/users/:id"
       :get users.read/read-users-by-id
       :route-name :read-users-by-id]
      ["/users/:id"
       :put (conj interceptors/common-interceptors users.update/update-users-by-id)
       :route-name :update-user]}))