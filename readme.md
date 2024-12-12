# Location and Category API

This API provides endpoints to manage locations and categories. It supports operations to create, update, delete, and retrieve locations and categories.

## Base URL

http://localhost:8080

---

## Endpoints

### Locations
[readme.md](readme.md)
1. **Get All Locations**
   `GET /api/locations

- **Query Parameters**:
    - `categoryId` (optional): Filter by category ID.
- **Response**: List of locations.

2. **Get Location by ID**
   `GET` /api/locations/{id}

- **Path Parameters**:
    - `id` (required): ID of the location.
- **Response**: Location details.

3. **Create Location**
   `POST` /api/locations

- **Request Body** (JSON):
  ```json
  {
    "name": "string",
    "categoryId": 1,
    "status": "PUBLIC or PRIVATE",
    "description": "string",
    "longitude": 12.3456,
    "latitude": 78.9101
  }
  ```
- **Response**: Created location details.

4. **Update Location**
   `PUT` /api/locations/{id}

- **Path Parameters**:
    - `id` (required): ID of the location.
- **Request Body** (JSON):
  ```json
  {
    "name": "string",
    "status": "PUBLIC or PRIVATE",
    "description": "string"
  }
  ```
- **Response**: Updated location details.

5. **Delete Location**
   `DELETE` /api/locations/{id}

- **Path Parameters**:
    - `id` (required): ID of the location.
- **Response**: Success message.

6. **Get Locations Within Radius**
   `GET` /api/locations/within-radius

- **Query Parameters**:
    - `longitude` (required): Longitude of the center point.
    - `latitude` (required): Latitude of the center point.
    - `radius` (required): Radius in kilometers.
- **Response**: List of locations within the radius.

7. **Get My Locations**
   `GET` /api/locations/my-locations

- **Response**: List of locations associated with the current user.

---

### Categories

1. **Get All Categories**
   `GET` /api/categories

- **Response**: List of categories.

2. **Get Category by ID**
   `GET` /api/categories/{id}


- **Path Parameters**:
    - `id` (required): ID of the category.
- **Response**: Category details.

3. **Create Category**
   POST /api/categories

- **Request Body** (JSON):
  ```json
  {
    "name": "string",
    "symbol": "string",
    "description": "string"
  }
  ```
- **Response**: Created category details.

---

### Get Address by Coordinates
**Get Address**
`GET` /api/get-address

**Query Parameters:**

- `latitude` (required): Latitude of the location (type: double).
- `longitude` (required): Longitude of the location (type: double).

**Response:**

Address of the location.

**Example:**

`GET` /api/get-address?latitude=59.3293&longitude=18.0686

**Response Body:**

```json
{
"address": "Stockholm, Sweden"
}
```
---