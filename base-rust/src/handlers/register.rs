use actix_web::{web, HttpResponse, Responder};
use uuid::Uuid;
use log::{info};
use crate::models::{RegisterRequest, RegisterResponse, ServiceInstance};
use crate::state::AppState;

pub async fn register_service(
    data: web::Data<AppState>,
    req: web::Json<RegisterRequest>,
) -> impl Responder {
    let mut services = data.services.lock().unwrap();
    let id = Uuid::new_v4().to_string();
    let service = ServiceInstance {
        id: id.clone(),
        name: req.name.clone(),
        address: req.address.clone(),
        port: req.port,
        last_heartbeat: std::time::SystemTime::now(),
    };
    services.insert(id.clone(), service.clone());
    info!("Registered service: {:?}", service);
    HttpResponse::Ok().json(RegisterResponse { id })
}