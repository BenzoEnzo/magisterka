use actix_web::{web, HttpResponse, Responder};
use log::{info, error};
use crate::models::DeregisterRequest;
use crate::state::AppState;

pub async fn deregister_service(
    data: web::Data<AppState>,
    req: web::Json<DeregisterRequest>,
) -> impl Responder {
    let mut services = data.services.lock().unwrap();
    if services.remove(&req.id).is_some() {
        info!("Deregistered service with ID: {}", req.id);
        HttpResponse::Ok().json("Service deregistered")
    } else {
        error!("Attempted to deregister non-existent service with ID: {}", req.id);
        HttpResponse::NotFound().json("Service not found")
    }
}