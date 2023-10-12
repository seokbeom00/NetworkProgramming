import os
import random

# 폴더 생성
if not os.path.exists("data"):
    os.makedirs("data")

# 파일내의 행과 열의 수
n = 100  # 예를 들어 10행
m = 300  # 예를 들어 5열

# 생성할 파일 수
count = 800

# 1 ~ 3000 사이의 숫자 중에서 count 개수만큼 랜덤하게 고릅니다.
selected_numbers = random.sample(range(1, 3001), count)


def find_c_d(number):
    if number % 60 == 0:
        c = 60
        d = number // 60
    else:
        c = number % 60
        d = number // 60 + 1
    return c, d


for number in selected_numbers:
    c, d = find_c_d(number)

    filename = f"data/file (c={c}) (d={d}).txt"

    # 파일을 쓰기 모드로 엽니다.
    with open(filename, "w") as f:
        # n행 m열의 랜덤한 숫자를 파일에 씁니다.
        for _ in range(n):
            line = " ".join(str(random.randint(0, 2000)) for _ in range(m))
            f.write(line + "\n")
