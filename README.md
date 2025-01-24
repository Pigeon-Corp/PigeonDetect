# PigeonDetect

PigeonDetect is an AI-powered tool designed to accurately detect and classify different species of pigeons using image data. It leverages machine learning and computer vision techniques to provide quick and reliable pigeon species identification.

## Features

- **High Accuracy**: Utilizes advanced machine learning models to ensure precise species identification.
- **Real-Time Detection**: Capable of processing images and providing results in real-time.
- **User-Friendly Interface**: Simple and intuitive interface for easy usage.
- **Scalable**: Can be integrated into larger systems or applications for comprehensive wildlife monitoring.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 3.6.0 or higher
- TensorFlow for Java
- Image dataset of pigeon species for training (if training from scratch)

### Installation

1. **Clone the repository**
    ```bash
    git clone https://github.com/pingvinkowalski/PigeonDetect.git
    cd PigeonDetect
    ```

2. **Build the project**
    ```bash
    mvn clean install
    ```

3. **Run the application**
    ```bash
    java -jar target/pigeon-detect-1.0.0.jar
    ```

### Docker

To run PigeonDetect using Docker, follow these steps:

1. **Build the Docker image**
    ```bash
    docker build -t pigeon-detect:latest .
    ```

2. **Run the Docker container**
    ```bash
    docker run -d -p 8080:8080 pigeon-detect:latest
    ```

## Usage

### Connecting to PigeonDetect

You can connect to PigeonDetect using any HTTP client. The default port is `8080`.

Example using `curl`:
```bash
curl -X POST "http://localhost:8080/predict" -F "file=@path_to_your_image.jpg"
