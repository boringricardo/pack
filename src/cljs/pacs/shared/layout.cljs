(ns pack.shared.layout
  (:require [pack.shared.flash :refer [flash-messages]]))

(defn layout
  [children]
  [:div
   [:div {:class "md:flex md:flex-col"}
    [:div {:class "md:h-screen md:flex md:flex-col"}
     [:div {:class "md:flex md:flex-shrink-0"}]
     [:div {:class "flex flex-grow overflow-hidden"}
      ;; To reset scroll region (https://inertiajs.com/pages#scroll-regions) add
      ;; `scroll-region="true"` to div below
      [:div {:class "w-full px-4 py-8 overflow-hidden overflow-y-auto md:p-12"}
       [:f> flash-messages]
       children]]]]])