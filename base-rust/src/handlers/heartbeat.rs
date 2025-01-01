use actix_web::{web, HttpResponse, Responder};

use crate::models::HeartbeatRequest;
use crate::state::AppState;

pub async fn heartbeat(
    data: web::Data<AppState>,
    req: web::Json<HeartbeatRequest>,
) -> impl Responder {
    let mut services = data.services.lock().unwrap();
    if let Some(service) = services.get_mut(&req.id) {
        service.last_heartbeat = std::time::SystemTime::now();
        HttpResponse::Ok().json("Heartbeat received")
    } else {
        HttpResponse::NotFound().json("Service not found")
    }
}