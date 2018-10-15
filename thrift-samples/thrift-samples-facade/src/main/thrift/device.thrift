namespace java com.github.tonydeng.demo.rpc.thrift


typedef i32 int

struct Device {
    1: string hostname,
    2: string type,
    3: string firm,
    4: int page,
    5: int id,
    6: list<string> keyword,
}

service DeviceService {

    Device getDevice(1:int id),
}