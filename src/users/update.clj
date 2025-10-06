(ns users.update
  (:require [clojure.data.json :as json]
            [helpers.file-path :as file-path]
            [schema.core :as s]
            [helpers.model-for-users :as model]))

(defn get-all []
  (json/read-json (slurp file-path/file-path)))

(defn get-user [id]
  (first (filter #(= (:id %) (read-string id)) (get-all))))

(defn update-users-by-id [request]
  (let [all-users (get-all)
        user (get-user (get-in request [:path-params :id]))
        body (:json-params request)
        new-age (:age body)
        old-age (:age user)
        name (:name user)
        user-id (:id user)
        user-updated (assoc user :age (:age (:json-params request)))
        without-old (remove #(= (:id %) (:id user)) all-users)
        updated-list (conj (vec without-old) user-updated)]

    (if (= (s/check model/schema-keyword-age body) nil)
      {:status  200
       :headers {"Content-Type" "application/json"}
       :body    {:message (str "Essa é minha request" body
                               "A idade do usuário " name
                               " com o id " user-id
                               " foi alterado de " old-age
                               " anos, para " new-age " anos com sucesso!")}}
      (println "Dado incorreto 3!"))

    (spit file-path/file-path
          (json/write-str updated-list))
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    {:message (str "Essa é minha request" body
                              "A idade do usuário " name
                              " com o id " user-id
                              " foi alterado de " old-age
                              " anos, para " new-age " anos com sucesso!")}}
    ))

#_(defn update-users-by-id [request]
    (let [all-users (get-all)
          user (get-user (get-in request [:path-params :id]))
          user-updated (assoc user :age (:age (:json-params request)))
          without-old (remove #(= (:id %) (:id user)) all-users)
          updated-list (conj (vec without-old) user-updated)]


      (if (contains? :age (:body (:json-params request)))
        (do (spit file-path/file-path (json/write-str updated-list))
            {:status  200
             :body    {:message "success"}})
        {:status  400
         :body    {:message "error"}})))