# Swater *scala written Engine*
[![Build status](https://ci.appveyor.com/api/projects/status/6xpene2748pnv812?svg=true)](https://ci.appveyor.com/project/hotung1027/Swater)
[![codecov](https://codecov.io/gh/hotung1027/Swater/branch/master/graph/badge.svg)](https://codecov.io/gh/hotung1027/Swater)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2702c33e3525454e94eb28eff4a8d2b1)](https://www.codacy.com/app/hotung1027/Swater?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hotung1027/Swater&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/2702c33e3525454e94eb28eff4a8d2b1)](https://www.codacy.com/app/hotung1027/Swater?utm_source=github.com&utm_medium=referral&utm_content=hotung1027/Swater&utm_campaign=Badge_Coverage)
[![CircleCI](https://circleci.com/gh/hotung1027/Swater.svg?style=svg)](https://circleci.com/gh/hotung1027/Swater)


experimental,constructing,welcome to contribute

# NMatrix *NumericMatrix*
a matrix defined by `number of columns` * `number of rows` and `Iterable[T]`
- **current only support for Numeric types**
- support basic numeric operation ` - + * / `

## Example
### A 4 * 3 Matrix defined as:
#### `number of columns = 4` * `number of rows =3` and 
#### `Iterable(1,2,3,4,5,6,7,8,9,10,11,12):Iterable[Int]`
      1   2   3   4
      _   _   _   _
    1|1   2   3   4
    2|5   6   7   8
    3|9   10  11  12

the Iterable start from ***Top-Left*** to ***Bottom-Right*** 
## A Matrix is also follow by these ruled values
#### Rows 
    Iterable(
    Iterable(1,2,3,4),
    Iterable(5,6,7,8),
    Iterable(9,10,11,12))
    
#### Columns
    Iterable(
    Iterable(1,5,9),
    Iterable(2,6,10),
    Iterable(3,7,11),
    Iterable(4,8,12))
    
# SquareMatrix *Perfect Sqaured Matrix*
#### supported operation:

- transpose
- determinant
- inverse

## Example 
#### A 4 * 4 matrix

      1   2   3   4
      _   _   _   _
    1|1   2   3   4
    2|5   6   7   8
    3|9   10  11  12
    4|13  14  15  16
    
#### A 3 * 3 matrix
      1   2   3 
      _   _   _
    1|1   2   3
    2|5   6   7 
    3|9   10  11

