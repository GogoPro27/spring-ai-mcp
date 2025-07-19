# AI Chat Client

A Spring Boot application that provides a web-based chat interface for interacting with OpenAI's GPT models.

## Setup

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd client-test
   ```

2. **Set up environment variables**
   ```bash
   cp .env.example .env
   ```

3. **Configure your OpenAI API key**
   - Get your API key from [OpenAI Platform](https://platform.openai.com/api-keys)
   - Edit the `.env` file and replace `your-openai-api-key-here` with your actual API key

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the chat interface**
   Open your browser and go to `http://localhost:8080`

## Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `OPENAI_API_KEY` | Your OpenAI API key | Yes |

## Security

- Never commit your `.env` file or API keys to version control
- The `.env` file is already included in `.gitignore`
- For production deployments, set environment variables through your hosting platform

## Features

- Modern, responsive chat interface
- Real-time communication with OpenAI GPT models
- Clean and intuitive user experience
- Mobile-friendly design
