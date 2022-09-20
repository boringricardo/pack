(ns pacs.middleware.inertia)

(defn wrap-inertia-share [handler _db]
  (fn [request]
    (let [success (-> request :flash :success)
          errors (-> request :flash :error)
          props {:errors (or errors {})
                 :flash {:success success
                         :error nil}}]
      (handler (assoc request :inertia-share props)))))
