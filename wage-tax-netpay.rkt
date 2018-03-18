;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname wage-tax-netpay) (read-case-sensitive #t) (teachpacks ((lib "convert.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "convert.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 2.3.1 in the book:
;; "HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing"

;; Demonstrates the use of auxiliary functions in the designing of
;; a program to calculate a sample of a real life problem

;; wage: number->number
;; Function that determines the wage of an employee
;; in relation to his work hours
;; Example: (= (wage 10) 120)
(define (wage h) (* h 12))

; Tests
(check-expect (wage 10) 120)
(check-expect (wage 20) 240)
(check-expect (wage 30) 360)

;; tax: number->number
;; Function that determines the tax an employee
;; must pay in relation to his work hours
;; Example: (= (tax 10) 120)
(define (tax h) (* (wage h) 0.15))

; Tests
(check-expect (tax 10) 18)
(check-expect (tax 20) 36)
(check-expect (tax 30) 54)

;; netpay: number->number
;; Function that determines the net pay of an employee
;; in relation to his work hours
;; Example: (= (netpay 10) 120)
(define (netpay h) (- (wage h) (tax h)))

;; Tests
(check-expect (netpay 10) 102)
(check-expect (netpay 20) 204)
(check-expect (netpay 30) 306)