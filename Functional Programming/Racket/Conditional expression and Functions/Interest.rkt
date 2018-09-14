;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname Interest) (read-case-sensitive #t) (teachpacks ((lib "convert.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "convert.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 4.4.1 in the book:
;; "HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing"
;; by M.Felleisen, R.B.Findler, M.Flatt, S.Krishnamurthi

;; This exercise is about conditional expressions, design of
;; programs for problems with diferent conditions

;; Problem 4.4.1: Develop the funtion interest. Like interest-rate, it
;; consumes a deposit amount. Instead of the rate, it produces the actual
;; amount of interest that the money earns in a year. The bank pays a flat
;; 4% for deposits of up to $1,000, a flat 4.5% per year for deposits of
;; up to $5,000, and a flat 5% for deposits of more than $5,000.

;; interest: number -> number
;; To calculate the amount of interest generated
;; in a year according to the deposited amount
;; Example: (= (interest 1000) 1040)
(define (interest amount)
  (cond
    [(<= amount 1000) (+ amount (* amount 0.04))]
    [(<= amount 5000) (+ amount (* amount 0.045))]
    [else (+ amount (* amount 0.05))]
  ))

;; Tests
(check-expect (interest 0) 0)
(check-expect (interest 1000) 1040)
(check-expect (interest 5000) 5225)
(check-expect (interest 10000) 10500)

;; Problem 4.4.2: Develop the funtion tax, which consumes the gross pay
;; and prduces the amount of tax owed. For a gross pay of $240 or less, 
;; the tax is 0%;for over $240 and $480 or less, the tax rate is 15%;
;; and for any pay over $480, the tax rate is 28%.
;; Also develop netpay. The function determines the net pay of an
;; employee from the number of hours worked. The net pay is the gross
;; pay minus the tax. Assume the hourly pay rate is $12.

;; tax: number -> number
;; Consumes the brutto pay and returns the amount of tax to deduct
;; Example: (= (tax 1000) 1040)
(define (tax grosspay)
  (cond
    [(<= grosspay 240) (* grosspay 0)]
    [(<= grosspay 480) (* grosspay 0.15)]
    [else (* grosspay 0.28)]
    )
  )
;; Tests
(check-expect (tax 0) 0)
(check-expect (tax 240) 0)
(check-expect (tax 480) 72)
(check-expect (tax 500) 140)

;; netpay: number -> number
;; calculates the pay after tax deduction from the number of working
;; hours
;; Example: (= (netpay 1000) 1040)
(define (netpay hours)
  (- (bruttopay hours) (tax (bruttopay hours)))
  )

;; Tests
(check-expect (netpay 0) 0)
(check-expect (netpay 10) 120)
(check-within (netpay 32) 326.4 0.1)
(check-within (netpay 56) 483.84 0.01)

;; bruttopay: number -> number
;; Calculates the pay before tax deduction
;; Example: (bruttopay 2) equals 24
(define (bruttopay h) (* h 12))

;; Tests
(check-expect (bruttopay 0) 0)
(check-expect (bruttopay 10) 120)
(check-expect (bruttopay 32) 384)
(check-expect (bruttopay 56) 672)188.16