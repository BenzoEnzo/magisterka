use std::collections::HashMap;
use std::sync::Mutex;

use crate::models::ServiceInstance;

pub struct AppState {
    pub services: Mutex<HashMap<String, ServiceInstance>>,
}

impl AppState {
    pub fn new() -> Self {
        AppState {
            services: Mutex::new(HashMap::new()),
        }
    }
}