# Use an official Python runtime as a parent image
FROM python:3.11-slim

# Set the working directory in the container
WORKDIR /app

# Copy the requirements file into the container
COPY requirements.txt .

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application code
COPY . .

# Expose the port FastAPI will run on
EXPOSE 8001

# Command to run the FastAPI application
CMD ["uvicorn", "predictor_service:app", "--host", "0.0.0.0", "--port", "8001"]