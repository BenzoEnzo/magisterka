use std::thread;
use std::time::{Duration, SystemTime};

use actix_web::web;
use crate::state::AppState;

pub fn start_health_checker(data: web::Data<AppState>) {
    let data_clone = data.clone();
    thread::spawn(move || loop {
        thread::sleep(Duration::from_secs(10));
        let mut services = data_clone.services.lock().unwrap();
        let now = SystemTime::now();
        services.retain(|_, service| {
            if let Ok(duration) = now.duration_since(service.last_heartbeat) {
                duration < Duration::from_secs(30)
            } else {
                false
            }
        });
    });
}