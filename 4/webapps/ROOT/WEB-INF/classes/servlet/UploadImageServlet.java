package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;

import service.UserService;
import domain.User;

/**
 * Класс UploadImageServlet обрабатывает загрузку изображений пользователями,
 * сохраняет файл и путь к аватару пользователя.
 */
// @WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024)
public class UploadImageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index");
            return;
        }

        String username = (String) session.getAttribute("user");
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            // Вместо getRealPath используем физический путь к папке uploads
            String uploadDir = getServletContext().getRealPath("/uploads");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            File file = new File(dir, fileName);
            try (InputStream in = filePart.getInputStream();
                 OutputStream out = new FileOutputStream(file)) {
                byte[] buf = new byte[8192];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }

            String avatarPath = "uploads/" + fileName;
            session.setAttribute("avatar", avatarPath);

            // Сохраняем путь к аватару в UserService
            UserService userService = (UserService) getServletContext().getAttribute("userService");
            if (userService != null) {
                User userObj = userService.getUser(username);
                if (userObj != null) {
                    userObj.setImagePath(avatarPath);
                }
            }
        }
        response.sendRedirect("profile.jsp");
    }
}
