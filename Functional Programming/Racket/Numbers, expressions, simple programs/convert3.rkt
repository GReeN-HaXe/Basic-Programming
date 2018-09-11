;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname convert3) (read-case-sensitive #t) (teachpacks ((lib "convert.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "convert.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 2.2.4 in the book:
;; " HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing "

;; Demonstrates the understanding of the text of a problem
;; and the conversion from text to a mathematical function.
;; Tests are also used to test diferent or special values.

;; convert3: number number number -> number
;; Takes three digits and returns a number 
;; with the order of the digits reversed.
;; Example: (= (convert3 1 2 3) 321)
(define (convert3 a b c)
  (+ (* c 100) (* b 10) a))

;; Tests
(check-expect (convert3 1 2 3) 321)
(check-expect (convert3 4 5 6) 654)
(check-expect (convert3 7 2 7) 727)