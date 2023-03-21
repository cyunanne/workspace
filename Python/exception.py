try:
    """예외가 발생할 가능성이 있는 코드"""
    print("num1 입력:")
    num1 = int(input())
    print("num2 입력:")
    num2 = int(input())
    print(num1 / num2)

except ValueError:
    """ValueError 예외 처리 코드"""

except ZeroDivisionError as e:
    """ZeroDivisionError 예외 처리 코드"""
    e.with_traceback()

except KeyboardInterrupt:
    print("KeyboardInterrupt 예외 처리 코드")

except:
    print("BaseExcepton 파생 예외 처리 코드")
    
finally:
    print("fianlly")

# help() : 도움말 출력 함수
#help(ValueError)    # Exception 상속
#help(Exception)      # BaseException 상송
#help(BaseException) # Object 상속
#help(SystemExit)

# 사용자 정의 예외 선언
def UserDefinedExcepton(Exception): 
    pass

