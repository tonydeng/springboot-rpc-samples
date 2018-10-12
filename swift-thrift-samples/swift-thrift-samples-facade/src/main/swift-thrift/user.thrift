namespace java com.github.tonydeng.demo.thrif.swift

typedef i32 int

struct User {
    1: int id,
    2: string name,
    3: int age,
}
service UserService {
    User getUser(1: int id),
}