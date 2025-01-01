use actix_web::{web, HttpResponse, Responder};
use log::{info};
use crate::models::ServiceInstance;
use crate::state::AppState;

pub async fn get_services(data: web::Data<AppState>) -> impl Responder {
    let services = data.services.lock().unwrap();
    let services: Vec<ServiceInstance> = services.values().cloned().collect();
    info!("Retrieved list of services: {} services found", services.len());
    HttpResponse::Ok().json(services)
}