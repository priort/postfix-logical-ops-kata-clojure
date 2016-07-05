(ns post-fix-booleans-clojure.boolean-postfix-test
  (:require [clojure.test :refer :all]
            [post-fix-booleans-clojure.boolean-postfix :as boolean-postfix]))

(deftest a-test
  (testing "post fix boolean expressions are evaluated
        1 for Boolean true,
        0 for Boolean false,
        A for logical and,
        R for logical or,
        X for logical exclusive-or,
        N for logical negation."
    (is (= true (boolean-postfix/eval "3 0 1 R")))
    (is (= false (boolean-postfix/eval "3 0 0 R")))
    (is (= true (boolean-postfix/eval "7 1 0 A 1 R N N")))
    (is (= true (boolean-postfix/eval "9 0 0 A 0 N 0 N A R")))
    (is (= false (boolean-postfix/eval "9 0 1 A 0 N 1 N A R")))
    (is (= false (boolean-postfix/eval "9 1 0 A 1 N 0 N A R")))
    (is (= true (boolean-postfix/eval "9 1 1 A 1 N 1 N A R")))
    ))
