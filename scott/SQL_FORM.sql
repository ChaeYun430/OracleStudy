//GROUP BY 절
별칭 사용 불가
다중행 함수를 사용하지 않은 일반 열은 GROUP BY절에 명시하지 않으면 SELECT절에서 사용할 수 없다. 


===8. JOIN===
FROM절에 테이블 또는 열과 행으로 구성된 데이터 집합 지정 가능(ex. 뷰, 서브쿼리)

//기준: 대상 데이터를 어떻게 연결하느냐에 따라
1. equi join

2. non-equi join 
BETWEEN [COLUMN] AND [COLUMN]

3. self join
같은 테이블 명시, 별칭 이용

4. outer join
조인 기준 열의 어느 한쪽이 NULL이어도 강제로 출력하는 방식
좌우의 기준이 모니터
WHERE [COLUMN] = [COLUMN](+) /WHERE [COLUMN](+) = [COLUMN]
전체 조인은 집합연산자 UNION 사용해 같은 효과 구현 가능


//SQL-99 표준 문법
1. NATURAL JOIN
이름과 자료형이 같은 열을 기준으로 등가 조인
기존 등가 조인과 다르게 조인 기준 열을 SELECT절에 명시할 때 테이블 이름을 붙이면 안 된다.
FROM [TABLE] NATURAL JOIN [TABLE]

2. JOIN ~ USING
FROM [TABLE] JOIN [TABLE] USING (COLUMN)

3. JOIN ~ ON
FROM [TABLE] JOIN [TABLE] ON [조건식]

4. OUTER JOIN
 FROM [TABLE] LEFT OUTER JOIN [TABLE] ON [조건식]
 FROM [TABLE] RIGHT OUTER JOIN [TABLE] ON [조건식]
 FROM [TABLE] FULL OUTER JOIN [TABLE] ON [조건식]

5. 세 개 이상의 테이블을 조인할 때
FROM [TABLE] JOIN [TABLE] ON [조건식] JOIN [TABLE] ON [조건식]


===9. SUBQUERY===

//정의
서브 쿼리: SQL문을 실행하는 데 필요한 데이터를 추가로 조회하기 위해 사용
메인 쿼리: 서브쿼리의 결과 값을 사용하여 기능을 수행하는 영역
1. 서브쿼리는 연산자와 같은 비교 또는 조회 대상의 오른쪽에 놓이며 괄호로 묶어서 사용
2. 특수한 몇몇 경우를 제외한 대부분의 서브쿼리에서는 ORDER BY 절 사용 불가
3. 서브쿼리의 SELECT절에 명시한 열은 메인쿼리의 비교 대상과 같은 자료형과 같은 개수로 지정해야 함.
4. 서브쿼리에 있는 SELECT문의 결과 행 수는 함께 사용하는 메인쿼리의 연산자 종류와 호환 가능해야 함.

//단일행 서브쿼리
단일행 연산자: 대소 비교 연산자, 동등 비교 연산자

//다중행 서브쿼리
다중행 연산자: 
IN: 메인쿼리의 데이터가 서브쿼리의 결과 중 하나라도 일치한 데이터가 있다면 참
ANY, SOME: 메인쿼리의 조건식을 만족하는 서브쿼리의 결과가 하나 이상이면 참 (하나의 값에 의해 영향을 받음)
ALL: 메인쿼리의 조건식을 서브쿼리의 결과의 모두가 만족하면 참 (전체 값의 영향을 받음)
EXISTS: 서브쿼리의 결과가 존재하면(즉, 행이 1개 이상이면) 참
활용: 특정 서브커리 결과 값의 존재 유무를 통해 메인쿼리의 데이터 노출 여부를 결정해야 할 때 간혹 사용

//다중열 서브쿼리
메인쿼리에 비교할 열을 괄호로 묶어 명시하고
서브쿼리에서는 괄호로 묶은 데이터와 같은 자료형 데이터를 SELECT절에 명시
SELECT [VALUE] FROM [테이블] WHERE ([COLUMN], [COLUMN]) IN ([SELECT문])

//INLINE-VIEW
FROM절에 사용하는 서브쿼리
SELECT문을 통해 일부 데이터 추출 후 별칭을 주어 사용
SELECT [VALUE] FROM ([SELECT문]) [별칭] WHERE [조건식]

//WITH 절
메인쿼리가 될 SELECT문 안에서 사용할 서브쿼리와 별칭을 먼저 지정
WITH [별칭] AS [SELECT문], ... SELECT [VALUE] FROM [별칭] WHERE [조건식]
	
//상호연관 서브쿼리
메인쿼리에 사용한 데이터를 서브쿼리에서 사용하고 서브쿼리의 결과 값을 다시 메인쿼리로 돌려주는 방식

//스칼라 서브쿼리
서브쿼리가 SELECT절의 하나의 열 영역으로서 결과 출력 가능
SELECT절에 명시하는 서브쿼리는 반드시 하나의 결과만 반환하도록 작성해주어야 한다. 
활용: 주 테이블의 값을 타 테이블의 값과 비교하는 조건식을 작성하고 싶을 때


===10.DML===

