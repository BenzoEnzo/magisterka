use actix_web::{web, App, HttpServer};

mod handlers;
mod models;
mod state;
mod health_checker;

use handlers::deregister::deregister_service;
use handlers::get_services::get_services;
use handlers::heartbeat::heartbeat;
use handlers::register::register_service;
use state::AppState;
use log::info;
use health_checker::start_health_checker;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    let app_state = web::Data::new(AppState::new());
    info!("Starting service registry server...");

    start_health_checker(app_state.clone());

    HttpServer::new(move || {
        App::new()
            .app_data(app_state.clone())
            .route("/register", web::post().to(register_service))
            .route("/deregister", web::post().to(deregister_service))
            .route("/services", web::get().to(get_services))
            .route("/heartbeat", web::post().to(heartbeat))
    })
        .bind(("127.0.0.1", 8000))?
        .run()
        .await
}