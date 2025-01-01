use serde::{Deserialize, Serialize};
use std::time::SystemTime;

#[derive(Serialize, Deserialize, Clone, Debug)]
pub struct ServiceInstance {
    pub id: String,
    pub name: String,
    pub address: String,
    pub port: u16,
    pub last_heartbeat: SystemTime,
}

#[derive(Serialize, Deserialize)]
pub struct RegisterRequest {
    pub name: String,
    pub address: String,
    pub port: u16,
}

#[derive(Serialize, Deserialize)]
pub struct RegisterResponse {
    pub id: String,
}

#[derive(Serialize, Deserialize)]
pub struct DeregisterRequest {
    pub id: String,
}

#[derive(Serialize, Deserialize)]
pub struct HeartbeatRequest {
    pub id: String,
}