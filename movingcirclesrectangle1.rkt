;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname movingcirclesrectangle1) (read-case-sensitive #t) (teachpacks ((lib "draw.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "draw.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 6.6 in the book:
;; " HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing "

;; This exercises is part 1 of the introduction of structures in the book
;; It gives insight on structures, the constructors and the selectors it creates.
;; It also allows a first touch with iterative refinement of programs.

;; Problem 6.6.1: Provide a structure definiton and a data definition for
;; representing colored circles. A circle is characterized by three pieces
;; of information: its center, its radius, and the color of its perimeter.
;; The first is a posn structure, the second a number, and the third a
;; (color) symbol.
;; Develop the template fun-for-circle, which outlines a function that
;; consumes circles. Its result is undetermined.

;; a circle is a structure: (make-circle ce ra co) where ce is a structure
;; (make-posn x y), ra is a number and co is a symbol.
(define-struct circle (center radius color))

;; Template
;; (define (fun-for-circle a-circle)
;;  ...(posn-x (circle-center a-circle))...
;;  ...(posn-y (circle-center a-circle))...
;;  ...(circle-radius a-circle)...
;;  ...(circle-color a-circle)...)

;; Problem 6.6.2: Use fun-for-circle to develop draw-a-circle. The function
;; consumes a circle structure and draws the corresponding circle on the screen.
;; Use (start 300 300) to create the canvas before testing the function.

;; draw-a-circle:: circle -> boolean
;; Takes a circle structure and draws it on a canvas
;; Example: (= (draw-a-circle (make-circle (make-posn 100 100) 50 'red)) true)
(define (draw-a-circle a-circle)
  (draw-circle (make-posn (posn-x (circle-center a-circle))
                          (posn-y (circle-center a-circle)))
               (circle-radius a-circle)
               (circle-color a-circle))
  )

;; Tests
(start 300 300)
(draw-a-circle (make-circle (make-posn 100 100) 50 'red))
(draw-a-circle (make-circle (make-posn 200 150) 30 'green))

;; Problem 6.6.3 Use the template fun-for-circle to develop in-circle?. The
;; function consumes a circle structure and a posn and determines whether or
;; not the pixel is inside the circle. All pixels whose distance to the center
;; is less or equal to the radius are inside the circle; the others are outside.
;; Consider the circle in the figure 14. The circle's center is (make-posn 6 2),
;; its radius is 1. The pixel labeled A, located at (make-posn 6 1.5), is inside
;; the circle. The pixel labeled B, located at (make-posn 8 6), is outside.

;; distance-to-center: circle pixel -> number
;; Takes a circle structure and a pixel structure and calculates the distance
;; between the pixel and the center
;; Example: (= (distance-to-center (make-circle (make-posn 100 100) 50 'red)
;;                                 (make-posn 100 105)) 5)
(define (distance-to-center a-circle a-pixel)
  (sqrt (+ (sqr (- (posn-x a-pixel)
                   (posn-x (circle-center a-circle))))
           (sqr (- (posn-y a-pixel)
                   (posn-y (circle-center a-circle))))))
  )

;; Tests
(check-within (distance-to-center (make-circle (make-posn 100 100) 50 'red)
                                  (make-posn 100 105)) 5 0.01)
(check-within (distance-to-center (make-circle (make-posn 100 100) 50 'red)
                                  (make-posn 150 75)) 55.90 0.01)

;; in-circle?: circle pixel -> boolean
;; Takes a circle structure and a pixel structure and determines if a
;; pixel is inside the circle.
;; Example: (= (in-circle? (make-circle (make-posn 100 100) 50 'red)
;;                                      (make-posn 100 105))) true)
(define (in-circle? a-circle a-pixel)
  (<= (distance-to-center a-circle a-pixel) (circle-radius a-circle)))

;; Tests
(check-expect (in-circle? (make-circle (make-posn 100 100) 50 'red)
                          (make-posn 100 105)) true)
(check-expect (in-circle? (make-circle (make-posn 100 100) 50 'red)
                          (make-posn 150 75)) false)

;; Problem 6.6.4: Use the template fun-for-circle to develop translate-circle.
;; The function consumes a circle structure and a number delta. The result is
;; a circle whose center is delta pixels to the right of the input. The function
;; has no effect on the canvas. Geometric Translation: Moving a geometric shape
;; along a straight line is referred to as translation.

;; translate-circle: circle number -> circle
;; Takes a circle and a number delta and translates the circle by delta.
;; Example: (= (translate-circle (make-circle (make-posn 100 100) 50 'red) 50)
;;             (make-circle (make-posn 150 100) 50 'red))
(define (translate-circle a-circle delta)
  (make-circle (make-posn (+ (posn-x (circle-center a-circle)) delta)
                          (posn-y (circle-center a-circle)))
               (circle-radius a-circle) (circle-color a-circle)))

;; Tests
(check-expect (translate-circle (make-circle (make-posn 100 100) 50 'red) 50)
              (make-circle (make-posn 150 100) 50 'red))
(check-expect (translate-circle (make-circle (make-posn 167 100) 50 'red) 13)
              (make-circle (make-posn 180 100) 50 'red))

;; Problem 6.6.5: Use the template fun-for-circle to develop clear-a-circle.
;; The function consumes a circle structure and clears the corresponding
;; circle on the canvas.

;; clear-a-circle: circle -> boolean
;; The function consumes a circle structure and clears the corresponding
;; circle on the canvas.
;; Example: (= (clear-a-circle (make-circle (make-posn 167 100) 50 'red)) true)
(define (clear-a-circle a-circle)
  (clear-circle (make-posn (posn-x (circle-center a-circle))
                           (posn-y (circle-center a-circle)))
                (circle-radius a-circle)
                (circle-color a-circle)))

;; Tests
(clear-a-circle (make-circle (make-posn 200 150) 30 'green))

;; Problem 6.6.6: Define the function draw-and-clear-circle, which draws a
;; circle structure, waits for a short time, and clears it. To implement
;; a waiting period, the teachpack draw.rkt provides the function
;; sleep-for-a-while. It consumes a number and puts a program to sleep
;; for that many seconds; its result is true. For example,
;; (sleep-for-a-while 1) waits for one second.

;; draw-and-clear-circle: a-circle -> true
;; The function takes a circle draws it on the canvas waits for a while
;; and then clears the circle.
;; Example: (= (draw-and-clear-circle (make-circle (make-posn 200 150) 30 'green))
;;             true)
(define (draw-and-clear-circle a-circle)
  (cond
    [(and (draw-a-circle a-circle) (sleep-for-a-while 1))
     (clear-a-circle a-circle)]))

;; Tests
(draw-and-clear-circle (make-circle (make-posn 175 110) 70 'blue))

(define (move-circle delta a-circle)
  (cond
    [(draw-and-clear-circle a-circle) (translate-circle a-circle delta)]
    [else a-circle]))

(draw-a-circle (move-circle 10
                            (move-circle 10
                                         (move-circle 10
                                           (make-circle (make-posn 175 110)
                                                        70 'blue)))))