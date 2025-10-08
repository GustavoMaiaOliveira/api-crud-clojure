(ns users.update
  (:require [clojure.data.json :as json]
            [helpers.file-path :as file-path]
            [helpers.model-for-users :as model]
            [schema.core :as s]))

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
      (do (spit file-path/file-path (json/write-str updated-list)) {:status  200
                                                                    :body    {:message (str "A idade do usuário " name " com o id " user-id ", foi alterado de " old-age " anos, para " new-age " anos com sucesso!")}})
      {:status  400
       :body    {:message (str "Falha ao alterar a idade do usuário " name " com o id " user-id ". Verifique se o campo “age” está preenchido corretamente.")}})))