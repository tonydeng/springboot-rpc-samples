# Spring Boot RPC's Samples

[![Build Status](https://travis-ci.org/tonydeng/springboot-rpc-samples.svg?branch=master)](https://travis-ci.org/tonydeng/springboot-rpc-samples)<br>
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.github.tonydeng.demo%3Aspringboot-rpc-samples&metric=coverage)](https://sonarcloud.io/dashboard?id=com.github.tonydeng.demo%3Aspringboot-rpc-samples)<br>
[![Lines of code](https://sonarcloud.io/api/project_badges/measure?project=com.github.tonydeng.demo%3Aspringboot-rpc-samples&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.github.tonydeng.demo%3Aspringboot-rpc-samples)<br>
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## RPC

`RPC`只是描绘了`Client`与`Server`之间的点对点调用流程，包括`stub`、通信、`RPC`消息解析等部分，在实际应用中，还需要考虑服务的高可用、负载均衡等问题，所以产品级的`RPC`框架除了点对点的`RPC`协议的具体实现外，还应包括服务的发现与注销、提供服务的多台`Server`的负载均衡、服务的高可用等更多的功能。目前的`RPC`框架大致有两种不同的侧重方向，一种偏重于服务治理，另一种偏重于跨语言调用。 

**服务治理型**的`RPC`框架有`Dubbo`、`DubboX`、`Motan`等，这类的`RPC`框架的特点是功能丰富，提供高性能的远程调用以及服务发现及治理功能，适用于大型服务的微服务化拆分以及管理，对于特定语言（如`Java`）的项目可以十分友好的透明化接入。但缺点是语言耦合度较高，跨语言支持难度较大。

跨语言调用型的`RPC`框架有`Thrift`、`gRPC`、`Hessian`、`Hprose`等，这一类的`RPC`框架重点关注于服务的跨语言调用，能够支持大部分的语言进行语言无关的调用，非常适合于为不同语言提供通用远程服务的场景。但这类框架没有服务发现相关机制，实际使用时一般需要代理层进行请求转发和负载均衡策略控制。

## Thrift

[Thrift介绍](docs/thrift.md)

## Armeria

[Armeria介绍](docs/armeria.md)

## GRpc

[GRpc介绍](docs/grpc.md)

## Gateway