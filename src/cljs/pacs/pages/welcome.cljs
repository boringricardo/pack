(ns pack.pages.welcome
  (:require ["@inertiajs/inertia-react" :refer [Head]]))

(defn index []
  [:<>
   [:> Head {:title "Welcome"}]
   [:div [:h1.mb-8.font-bold.text-3xl "Welcome to pack"]
    [:p.mb-8.leading-normal "Hey there! Welcome to the " [:b "Clojure / ClojureScript Reagent / Inertia"] " starter. "]
    [:p.mb-8.leading-normal "Everything is ready for you to start working on your next big idea."]]])